package com.Sor.Model;

import java.util.*;

public class Worked {

	private String workedId = null;
	private String organizationId = null;
	private Job job=new Job();
	private String beginDate = null;
	private String endDate = null;
	private String homepage;
	
	/**
	 * Job of the person
	 **/

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	
	/**
	 * Unique identifier.
	 **/

	public String getWorkedId() {
		return workedId;
	}

	public void setWorkedId(String workedId) {
		this.workedId = workedId;
	}

	/**
	 * the id of the organization
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

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String o) {
		this.beginDate = o;
	}

	/**
	 * the end date of the job , if still working there it's empty
	 **/

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
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
		Worked worked = (Worked) o;
		return Objects.equals(workedId, worked.workedId) && Objects.equals(organizationId, worked.organizationId)
				&& Objects.equals(worked, worked.job)
				&& Objects.equals(beginDate, worked.beginDate) && Objects.equals(endDate, worked.endDate)
				&& Objects.equals(homepage, worked.homepage);
	}

	@Override
	public int hashCode() {
		return Objects.hash(workedId, organizationId, job,  beginDate, endDate,homepage);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Worked {\n");

		sb.append("    workedId: ").append(toIndentedString(workedId)).append("\n");
		sb.append("    organizationId: ").append(toIndentedString(organizationId)).append("\n");
		sb.append("    job: ").append(toIndentedString(job)).append("\n");
		sb.append("    beginDate: ").append(toIndentedString(beginDate)).append("\n");
		sb.append("    endDate: ").append(toIndentedString(endDate)).append("\n");
		sb.append("    homepage: ").append(toIndentedString(homepage)).append("\n");
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

	public void setHomepage(String homepage) {
		this.homepage=homepage;
		
	}
	public String getHomepage() {
		return homepage;
		
	}
}
