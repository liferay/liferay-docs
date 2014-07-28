/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.samples.portlet.eventlisting.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.samples.portlet.eventlisting.model.Location;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Location in entity cache.
 *
 * @author Joe Bloggs
 * @see Location
 * @generated
 */
public class LocationCacheModel implements CacheModel<Location>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{locationId=");
		sb.append(locationId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", streetAddress=");
		sb.append(streetAddress);
		sb.append(", city=");
		sb.append(city);
		sb.append(", stateOrProvince=");
		sb.append(stateOrProvince);
		sb.append(", country=");
		sb.append(country);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Location toEntityModel() {
		LocationImpl locationImpl = new LocationImpl();

		locationImpl.setLocationId(locationId);

		if (name == null) {
			locationImpl.setName(StringPool.BLANK);
		}
		else {
			locationImpl.setName(name);
		}

		if (description == null) {
			locationImpl.setDescription(StringPool.BLANK);
		}
		else {
			locationImpl.setDescription(description);
		}

		if (streetAddress == null) {
			locationImpl.setStreetAddress(StringPool.BLANK);
		}
		else {
			locationImpl.setStreetAddress(streetAddress);
		}

		if (city == null) {
			locationImpl.setCity(StringPool.BLANK);
		}
		else {
			locationImpl.setCity(city);
		}

		if (stateOrProvince == null) {
			locationImpl.setStateOrProvince(StringPool.BLANK);
		}
		else {
			locationImpl.setStateOrProvince(stateOrProvince);
		}

		if (country == null) {
			locationImpl.setCountry(StringPool.BLANK);
		}
		else {
			locationImpl.setCountry(country);
		}

		locationImpl.setCompanyId(companyId);
		locationImpl.setGroupId(groupId);
		locationImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			locationImpl.setCreateDate(null);
		}
		else {
			locationImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			locationImpl.setModifiedDate(null);
		}
		else {
			locationImpl.setModifiedDate(new Date(modifiedDate));
		}

		locationImpl.resetOriginalValues();

		return locationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		locationId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		streetAddress = objectInput.readUTF();
		city = objectInput.readUTF();
		stateOrProvince = objectInput.readUTF();
		country = objectInput.readUTF();
		companyId = objectInput.readLong();
		groupId = objectInput.readLong();
		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(locationId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (streetAddress == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(streetAddress);
		}

		if (city == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(city);
		}

		if (stateOrProvince == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(stateOrProvince);
		}

		if (country == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(country);
		}

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
	}

	public long locationId;
	public String name;
	public String description;
	public String streetAddress;
	public String city;
	public String stateOrProvince;
	public String country;
	public long companyId;
	public long groupId;
	public long userId;
	public long createDate;
	public long modifiedDate;
}