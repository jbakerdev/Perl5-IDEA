/*
 * Copyright 2015-2018 Alexandr Evstigneev
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

package com.perl5.lang.perl.adapters;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.projectRoots.Sdk;
import com.perl5.PerlBundle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CpanAdapter extends PackageManagerAdapter {
  private static final String PACKAGE_NAME = "CPAN";
  public static final String SCRIPT_NAME = "cpan";

  public CpanAdapter(@NotNull Sdk sdk, @NotNull Project project) {
    super(sdk, project);
  }

  @NotNull
  @Override
  public String getPresentableName() {
    return "cpan";
  }

  @NotNull
  @Override
  protected String getManagerScriptName() {
    return SCRIPT_NAME;
  }

  @NotNull
  @Override
  protected String getManagerPackageName() {
    return PACKAGE_NAME;
  }

  @NotNull
  public static AnAction createInstallAction(@NotNull Sdk sdk,
                                             @NotNull Project project,
                                             @NotNull String libraryName,
                                             @Nullable Runnable actionCallback) {
    return new DumbAwareAction(PerlBundle.message("perl.quickfix.install.family", SCRIPT_NAME)) {
      @Override
      public void actionPerformed(@NotNull AnActionEvent e) {
        new CpanAdapter(sdk, project).install(libraryName);
        if (actionCallback != null) {
          actionCallback.run();
        }
      }
    };
  }
}