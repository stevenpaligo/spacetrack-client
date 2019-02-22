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

/**
 * Represents a source for <a href="https://www.space-track.org/">Space-Track.org</a> credentials
 * 
 * @author Steven Paligo
 */
public interface CredentialProvider {

  /**
   * The <a href="https://www.space-track.org/">Space-Track.org</a> user name to be used for queries
   * 
   * @return A non-null user name
   */
  public String getUserName();


  /**
   * The <a href="https://www.space-track.org/">Space-Track.org</a> password to be used for queries
   * 
   * @return A non-null password
   */
  public String getPassword();

}
