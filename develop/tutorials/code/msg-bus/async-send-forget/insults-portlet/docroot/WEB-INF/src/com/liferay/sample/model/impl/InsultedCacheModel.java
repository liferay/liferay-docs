/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.sample.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.sample.model.Insulted;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Insulted in entity cache.
 *
 * @author nickgaskill
 * @see Insulted
 * @generated
 */
public class InsultedCacheModel implements CacheModel<Insulted>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{insultedId=");
		sb.append(insultedId);
		sb.append(", insult=");
		sb.append(insult);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Insulted toEntityModel() {
		InsultedImpl insultedImpl = new InsultedImpl();

		insultedImpl.setInsultedId(insultedId);

		if (insult == null) {
			insultedImpl.setInsult(StringPool.BLANK);
		}
		else {
			insultedImpl.setInsult(insult);
		}

		insultedImpl.setGroupId(groupId);
		insultedImpl.setCompanyId(companyId);
		insultedImpl.setUserId(userId);

		insultedImpl.resetOriginalValues();

		return insultedImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		insultedId = objectInput.readLong();
		insult = objectInput.readUTF();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(insultedId);

		if (insult == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(insult);
		}

		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);
	}

	public long insultedId;
	public String insult;
	public long groupId;
	public long companyId;
	public long userId;
}