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
package com.stevenpaligo.spacetrack.client;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stevenpaligo.spacetrack.client.query.QueryField;
import lombok.NonNull;

public class QueryFieldEnumValidator {

  private QueryFieldEnumValidator() {

    // enforce static method usage
  }


  public static void validate(@NonNull Class<? extends QueryField> queryFieldEnum, @NonNull Class<?> resultType) throws Exception {

    // filter the result type's fields down to those needing validation
    // (the static fields are the JSON property names)
    Field[] resultTypeFields = Arrays.stream(resultType.getDeclaredFields()).filter(f -> Modifier.isStatic(f.getModifiers()) == false).toArray(Field[]::new);


    // index the result type fields by their JSON property names
    Map<String, Field> jsonNameToResultTypeField = new HashMap<>();

    for (Field resultTypeField : resultType.getDeclaredFields()) {

      // exclude static fields
      if (Modifier.isStatic(resultTypeField.getModifiers())) {

        continue;
      }


      // get the JsonProperty annotation
      JsonProperty jsonProperty = resultTypeField.getAnnotation(JsonProperty.class);

      if (jsonProperty == null) {

        throw new Exception("One of the result type fields does not have a @JsonProperty annotation: " + resultTypeField.getName());
      }


      // get the JSON property name
      String jsonName = jsonProperty.value();

      if (jsonName.equals(JsonProperty.USE_DEFAULT_NAME)) {

        throw new Exception("One of the result type fields does not declare a JSON property name in its @JsonProperty annotation: " + resultTypeField.getName());
      }


      // map the property name to the field
      Field previouslyMappedField = jsonNameToResultTypeField.put(jsonName, resultTypeField);

      if (previouslyMappedField != null) {

        throw new Exception("One of the result type fields has a duplicate JSON property name in its @JsonProperty annotation: " + resultTypeField.getName());
      }
    }


    // verify the query field enum matches the result type
    if (queryFieldEnum.getEnumConstants().length != resultTypeFields.length) {

      throw new Exception("The query field enum and result type have different numbers of fields (query field enum: " + queryFieldEnum.getEnumConstants().length + ", result type: " + resultTypeFields.length + ")");
    }

    for (QueryField enumValue : queryFieldEnum.getEnumConstants()) {

      String enumValueName = enumValue.toString();
      String enumValueQueryFieldName = enumValue.getQueryFieldName();


      // get the result type's field that has the same JSON name as the enum value
      Field resultTypeField = jsonNameToResultTypeField.get(enumValueQueryFieldName);

      if (resultTypeField == null) {

        throw new Exception("A query field enum value does not exist in the result type: " + enumValueName);
      }


      // verify the enum value's name matches the result type field's name
      if (enumValueName.replace("_", "").toLowerCase().equals(resultTypeField.getName().toLowerCase()) == false) {

        throw new Exception("A query field enum value's name does not match the name of the corresponding field in the result type: " + enumValueName);
      }
    }
  }
}
