package com.river.videriTest.support;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.spi.MetadataBuildingContext;

public class RiverNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {

	private static final long serialVersionUID = -5780318166029693694L;

	@Override
	protected Identifier toIdentifier(String stringForm, MetadataBuildingContext buildingContext) {
		// TODO Auto-generated method stub
		return super.toIdentifier("videri_" + stringForm, buildingContext);
	}
}
