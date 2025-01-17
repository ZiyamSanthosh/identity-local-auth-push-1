package org.wso2.carbon.identity.local.auth.push.servlet.constant;

/**
 * Constants for Push Servlet.
 */
public class PushServletConstants {

    public static final String PUSH_AUTH_BASE_URL = "/push-auth";
    public static final String PUSH_AUTHENTICATE_SERVLET_URL = PUSH_AUTH_BASE_URL + "/authenticate";
    public static final String PUSH_STATUS_SERVLET_URL = PUSH_AUTH_BASE_URL + "/check-status";

    public static final String MEDIA_TYPE_JSON = "application/json";
    public static final String AUTH_RESPONSE = "authResponse";

    public static final String TOKEN_DEVICE_ID = "did";
    public static final String TOKEN_PUSH_AUTH_ID = "pid";
    public static final String TOKEN_TENANT_DOMAIN = "td";

    /**
     * Object holding authentication mobile response status.
     */
    public enum Status {
        COMPLETED, PENDING
    }

    /**
     * Error messages for Push Servlet.
     */
    public enum ErrorMessages {

        ERROR_CODE_PUSH_AUTH_ID_NOT_FOUND_IN_STATUS(
                "PBA-15001",
                "Error occurred when checking authentication status. The pushAuthId was null or "
                        + "the HTTP request was not supported."
        ),
        ERROR_CODE_GET_DEVICE_ID_FAILED(
                "PBA-15002",
                "Error occurred when extracting custom claim value of %s from auth response token."
        ),
        ERROR_CODE_GET_PUBLIC_KEY_FAILED(
                "PBA-15003",
                "Error occurred when trying to get the public key from device: %s."
        ),
        ERROR_CODE_TOKEN_VALIDATION_FAILED(
                "PBA-15004",
                "Error occurred when validating auth response token from device: %s."
        ),
        ERROR_CODE_PARSE_JWT_FAILED(
                "PBA-15005",
                "Error occurred when parsing auth response token to JWT."
        ),
        ERROR_CODE_AUTH_RESPONSE_TOKEN_NOT_FOUND(
                "PBA-15006",
                "The request did not contain an authentication response token"
        ),
        ERROR_CODE_PUSH_AUTH_ID_NOT_FOUND(
                "PBA-15007",
                "Authentication response token received from device: %s does not contain a pushAuthId."
        ),
        ERROR_CODE_TENANT_ID_NOT_FOUND(
                "PBA-15008",
                "Authentication response token received from device: %s does not contain a tenantDomain."
        ),;

        private final String code;
        private final String message;

        ErrorMessages(String code, String message) {

            this.code = code;
            this.message = message;
        }

        public String getCode() {

            return code;
        }

        public String getMessage() {

            return message;
        }

        @Override
        public String toString() {

            return code + " - " + message;
        }
    }

}
