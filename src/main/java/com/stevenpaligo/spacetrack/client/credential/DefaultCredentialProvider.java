package com.stevenpaligo.spacetrack.client.credential;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@Getter
@AllArgsConstructor
public class DefaultCredentialProvider implements CredentialProvider {

  @NonNull
  private String userName;

  @NonNull
  private String password;

}
