/*
 * The author licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stevenpaligo.spacetrack.client.credential;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

/**
 * An implementation of the {@link CredentialProvider} interface to hold explicitly stated credentials
 * 
 * @author Steven Paligo
 */
@Getter
@AllArgsConstructor
public class DefaultCredentialProvider implements CredentialProvider {

  @NonNull
  private String userName;

  @NonNull
  private String password;

}
