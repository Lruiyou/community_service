package com.alan.project.enums;

public enum ExitStatus {
      EXIT(1),
      UNEXIT(0);

      private int status;

      ExitStatus(int status){
            this.status = status;
      }

      public int getStatus() {
            return status;
      }
}
