/*
 * Copyright 2015-2020 Alexandr Evstigneev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.perl5.lang.perl;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import com.perl5.lang.perl.lexer.PerlElementTypes;
import com.perl5.lang.perl.lexer.PerlLexer;
import com.perl5.lang.perl.lexer.PerlLexingContext;
import com.perl5.lang.perl.lexer.PerlTokenSets;
import com.perl5.lang.perl.lexer.adapters.PerlMergingLexerAdapter;
import com.perl5.lang.perl.parser.PerlParserImpl;
import com.perl5.lang.perl.parser.elementTypes.PsiElementProvider;
import com.perl5.lang.perl.psi.PerlLexerAwareParserDefinition;
import com.perl5.lang.perl.psi.impl.PerlFileImpl;
import com.perl5.lang.perl.psi.stubs.PerlStubElementTypes;
import org.jetbrains.annotations.NotNull;

import static com.perl5.lang.perl.lexer.PerlLexer.*;
import static com.perl5.lang.perl.lexer.PerlTokenSets.HEREDOC_BODIES_TOKENSET;

public class PerlParserDefinition implements ParserDefinition, PerlElementTypes, PerlLexerAwareParserDefinition {
  public static final TokenSet WHITE_SPACES = TokenSet.create(
    TokenType.WHITE_SPACE
  );
  public static final TokenSet COMMENTS = TokenSet.orSet(
    HEREDOC_BODIES_TOKENSET,
    TokenSet.create(
      COMMENT_LINE, COMMENT_BLOCK, COMMENT_ANNOTATION,
      HEREDOC_END, HEREDOC_END_INDENTABLE
    )
  );

  public static final TokenSet WHITE_SPACE_AND_COMMENTS = TokenSet.orSet(WHITE_SPACES, COMMENTS);

  public static final TokenSet MEANINGLESS_TOKENS = TokenSet.orSet(
    WHITE_SPACE_AND_COMMENTS,
    TokenSet.create(POD)
  );

  // fixme inline this
  public static final TokenSet LITERALS = PerlTokenSets.STRING_CONTENT_TOKENSET;
  public static final TokenSet IDENTIFIERS = TokenSet.orSet(
    PerlTokenSets.VARIABLE_NAMES,
    TokenSet.create(SUB_NAME, QUALIFYING_PACKAGE, PACKAGE, IDENTIFIER));

  @Override
  public @NotNull Lexer createLexer(Project project) {
    return new PerlMergingLexerAdapter(PerlLexingContext.create(project));
  }

  @Override
  public @NotNull TokenSet getWhitespaceTokens() {
    return WHITE_SPACES;
  }

  @Override
  public @NotNull TokenSet getCommentTokens() {
    return COMMENTS;
  }

  @Override
  public @NotNull TokenSet getStringLiteralElements() {
    return LITERALS;
  }

  @Override
  public @NotNull PsiParser createParser(final Project project) {
    return PerlParserImpl.INSTANCE;
  }

  @Override
  public IFileElementType getFileNodeType() {
    return PerlStubElementTypes.FILE;
  }

  @Override
  public PsiFile createFile(FileViewProvider viewProvider) {
    return new PerlFileImpl(viewProvider);
  }

  @Override
  public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
    return SpaceRequirements.MAY;
  }

  @Override
  public @NotNull PsiElement createElement(ASTNode node) {
    try {
      return ((PsiElementProvider)node.getElementType()).getPsiElement(node);
    }
    catch (Exception e) {
      throw new RuntimeException("Problem with node " + node, e);
    }
  }

  @Override
  public int getLexerStateFor(@NotNull ASTNode contextNode, @NotNull IElementType elementType) {
    if (elementType == HASH_INDEX) {
      return AFTER_VARIABLE;
    }
    else if (elementType == COMMENT_ANNOTATION) {
      return ANNOTATION;
    }
    else if (elementType == HEREDOC_QQ) {
      return STRING_QQ;
    }
    else if (elementType == HEREDOC_QX) {
      return STRING_QX;
    }
    else if (elementType == HEREDOC) {
      return STRING_Q;
    }
    else if (elementType == PARSABLE_STRING_USE_VARS) {
      return USE_VARS_STRING;
    }
    return PerlLexer.YYINITIAL;
  }
}