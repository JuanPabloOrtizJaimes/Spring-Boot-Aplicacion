package com.juanOrtizJaimes.springBootAplicacion.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ChangePasswordForm {

	@NotNull
	private Long id;
	@NotBlank(message = "Esta vacio current")
	private String currentPassword;
	@NotBlank(message = "Esta vacio new")
	private String newPassword;
	@NotBlank(message = "Esta vacio confirm")
	private String newConfirmPassword;
	
	public ChangePasswordForm() {}
	public ChangePasswordForm(Long id) {this.id=id;}
	public Long getId() {
		return id;
	}
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewConfirmPassword() {
		return newConfirmPassword;
	}
	public void setNewConfirmPassword(String newConfirmPassword) {
		this.newConfirmPassword = newConfirmPassword;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		return Objects.hash(currentPassword, id, newConfirmPassword, newPassword);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChangePasswordForm other = (ChangePasswordForm) obj;
		return Objects.equals(currentPassword, other.currentPassword) && Objects.equals(id, other.id)
				&& Objects.equals(newConfirmPassword, other.newConfirmPassword)
				&& Objects.equals(newPassword, other.newPassword);
	}
	@Override
	public String toString() {
		return "ChangePasswordForm [id=" + id + ", currentPassword=" + currentPassword + ", newPassword=" + newPassword
				+ ", newConfirmPassword=" + newConfirmPassword + "]";
	}
	
	
	
	
	
}
