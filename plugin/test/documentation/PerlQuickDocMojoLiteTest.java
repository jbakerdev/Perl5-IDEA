/*
 * Copyright 2015-2019 Alexandr Evstigneev
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

package documentation;


import base.PerlLightTestCase;
import org.junit.Test;

public class PerlQuickDocMojoLiteTest extends PerlLightTestCase {
  @Override
  protected String getBaseDataPath() {
    return "testData/documentation/perl/quickdoc/mojoLite";
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    withMojo();
  }

  @Test
  public void testApp() {
    doTest();
  }

  @Test
  public void testNew() {
    doTest();
  }

  @Test
  public void testDel() {
    doTest();
  }

  @Test
  public void testGroup() {
    doTest();
  }

  @Test
  public void testAny() {
    doTest();
  }

  @Test
  public void testGet() {
    doTest();
  }

  @Test
  public void testOptions() {
    doTest();
  }

  @Test
  public void testPatch() {
    doTest();
  }

  @Test
  public void testPost() {
    doTest();
  }

  @Test
  public void testPut() {
    doTest();
  }

  @Test
  public void testWebsocket() {
    doTest();
  }

  @Test
  public void testUnder() {
    doTest();
  }

  @Test
  public void testHelper() {
    doTest();
  }

  @Test
  public void testHook() {
    doTest();
  }

  @Test
  public void testPlugin() {
    doTest();
  }

  private void doTest() {
    initWithTextSmartWithoutErrors("package Sandbox; use Mojolicious::Lite;\n" +
                                   "\n" +
                                   "<caret>"+getTestName(true)+";\n");
    doTestQuickDocWithoutInit();
  }
}
