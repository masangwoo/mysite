package com.poscoict.mysite.vo;

public class SiteVo {
	String title;
	String welcome;
	String profile;

	String description;
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}

	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWelcome() {
		return welcome;
	}
	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "SiteVo [title=" + title + ", welcome=" + welcome + ", description=" + description + ",profile="+profile+"]";
	}
}