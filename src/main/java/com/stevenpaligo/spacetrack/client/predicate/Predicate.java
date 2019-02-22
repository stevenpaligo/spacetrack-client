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
package com.stevenpaligo.spacetrack.client.predicate;

import com.stevenpaligo.spacetrack.client.query.QueryField;

/**
 * Represents a predicate used for filtering results
 * 
 * @author Steven Paligo
 */
public interface Predicate<T extends QueryField> {

  /**
   * Get the URL component the represents this predicate
   * 
   * @return A non-null query parameter in the form of a URL component
   */
  public String toQueryParameter();

}
