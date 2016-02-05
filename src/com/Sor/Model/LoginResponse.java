package com.Sor.Model;

import java.util.Objects;

public class LoginResponse {
	private String userId = null;
	private String userType = null;
	private Boolean logedIn = false;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Boolean getLogedin() {
		return logedIn;
	}

	public void setLogedin(Boolean logedin) {
		this.logedIn = logedin;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		LoginResponse loginResponse = (LoginResponse) o;
		return Objects.equals(userId, loginResponse.userId) && Objects.equals(userType, loginResponse.userType)
				&& Objects.equals(logedIn, loginResponse.logedIn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, userType, logedIn);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class LoginResponse {\n");

		sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
		sb.append("    userType: ").append(toIndentedString(userType)).append("\n");
		sb.append("    logedIn: ").append(toIndentedString(logedIn)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
