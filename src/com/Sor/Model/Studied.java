package com.Sor.Model;

import java.util.Date;
import java.util.Objects;

public class Studied {
	private String studiedId = null;
	private String organizationId = null;
	private Date beginDate = null;
	private Date endDate = null;
	private String homepage=null;
	/**
	 * Unique identifier.
	 **/

	public String getStudiedId() {
		return studiedId;
	}

	public void setStudiedId(String studiedId) {
		this.studiedId = studiedId;
	}
	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	/**
	 * the name of the Knowledge
	 **/

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	/**
	 * the stating date of the job
	 **/

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	/**
	 * the end date of the job , if still working there it's empty
	 **/

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Studied studied = (Studied) o;
		return Objects.equals(studiedId, studied.studiedId) && Objects.equals(organizationId, studied.organizationId)
				&& Objects.equals(beginDate, studied.beginDate) && Objects.equals(endDate, studied.endDate)
				&& Objects.equals(homepage, studied.homepage);
	}

	@Override
	public int hashCode() {
		return Objects.hash(studiedId, organizationId,homepage, beginDate, endDate);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Studied {\n");

		sb.append("    studiedId: ").append(toIndentedString(studiedId)).append("\n");
		sb.append("    organizationId: ").append(toIndentedString(organizationId)).append("\n");
		sb.append("    homepage: ").append(toIndentedString(homepage)).append("\n");
		sb.append("    beginDate: ").append(toIndentedString(beginDate)).append("\n");
		sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");

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
