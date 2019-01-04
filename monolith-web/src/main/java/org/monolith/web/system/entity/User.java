package org.monolith.web.system.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.monolith.core.entity.BaseEntity;

import com.alibaba.fastjson.annotation.JSONField;

public class User extends BaseEntity {

	private Integer id;
	@NotBlank(message = "{user.name.not.blank}", groups = { Insert.class })
	@Length(min = 0, max = 16, message = "{user.name.length}", groups = { Insert.class })
	private String name;
	@NotNull(message = "{user.sex.not.null}", groups = { Insert.class })
	private Integer sex;
	@NotBlank(message = "{user.idCard.not.blank}", groups = { Insert.class })
	private String idCard;
	@JSONField(format = "yyyy-MM-dd")
	@NotNull(message = "{user.birthday.not.null}", groups = { Insert.class })
	@Past(message = "{user.birthday.past}", groups = { Insert.class })
	private Date birthday;
	@NotBlank(message = "{user.phone.not.blank}", groups = { Insert.class })
	private String phone;
	private String province;
	private String city;
	private String area;
	private String street;
	private Double lng;
	private Double lat;
	private String address;
	private Date createDate;
	private Date updateDate;
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	@NotNull(message = "{page.empty}", groups = { SelectAll.class })
	public Integer getPage() {
		return super.getPage();
	}

	@Override
	@NotNull(message = "{rows.empty}", groups = { SelectAll.class })
	public Integer getRows() {
		return super.getRows();
	}
}
