package com.Sor.Model;

import java.util.Objects;

public class RegisterResponse {
	private int userId = 0;
	private String userType = null;
	private Boolean logedIn = false;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int id) {
		this.userId = id;
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
		RegisterResponse registerResponse = (RegisterResponse) o;
		return Objects.equals(userId, registerResponse.userId) && Objects.equals(userType, registerResponse.userType)
				&& Objects.equals(logedIn, registerResponse.logedIn);
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, userType, logedIn);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class RegisterResponse {\n");

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
