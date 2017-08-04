/*
 * Copyright 2015-2017 Alexandr Evstigneev
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

package com.perl5.lang.tt2.idea.settings;

import com.intellij.openapi.actionSystem.CustomShortcutSet;
import com.intellij.ui.JBColor;
import com.perl5.PerlBundle;
import com.perl5.PerlIcons;
import com.perl5.lang.perl.idea.configuration.settings.sdk.PerlSourceRootEditHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class TemplateToolkitSourceRootEditHandler extends PerlSourceRootEditHandler {
  public TemplateToolkitSourceRootEditHandler() {
    super(TemplateToolkitSourceRootType.INSTANCE);
  }

  @NotNull
  @Override
  public String getRootTypeName() {
    return PerlBundle.message("ttk2.root.type");
  }

  @NotNull
  @Override
  public Icon getRootIcon() {
    return PerlIcons.TTK2_ROOT;
  }

  @Nullable
  @Override
  public Icon getFolderUnderRootIcon() {
    return null;
  }

  @Nullable
  @Override
  public CustomShortcutSet getMarkRootShortcutSet() {
    return null;
  }

  @NotNull
  @Override
  public String getRootsGroupTitle() {
    return PerlBundle.message("perl.root.group.template");
  }

  @NotNull
  @Override
  public Color getRootsGroupColor() {
    return new JBColor(new Color(76, 94, 133), new Color(76, 94, 133));
  }

  @NotNull
  @Override
  public String getUnmarkRootButtonText() {
    return PerlBundle.message("ttk2.root.unmark");
  }
}
