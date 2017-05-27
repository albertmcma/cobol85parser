/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.condition.impl;

import io.proleap.cobol.Cobol85Parser.ClassConditionContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.type.CobolBaseType;
import io.proleap.cobol.asg.metamodel.type.Type;
import io.proleap.cobol.asg.metamodel.valuestmt.condition.ClassCondition;
import io.proleap.cobol.asg.metamodel.valuestmt.impl.ValueStmtImpl;

public class ClassConditionImpl extends ValueStmtImpl implements ClassCondition {

	protected Call classCall;

	protected ClassConditionType classConditionType;

	protected ClassConditionContext ctx;

	protected Call identifierCall;

	protected boolean not;

	public ClassConditionImpl(final ProgramUnit programUnit, final ClassConditionContext ctx) {
		super(programUnit, ctx);
	}

	@Override
	public Call getClassCall() {
		return classCall;
	}

	@Override
	public ClassConditionType getClassConditionType() {
		return classConditionType;
	}

	@Override
	public Call getIdentifierCall() {
		return identifierCall;
	}

	@Override
	public boolean getNot() {
		return not;
	}

	@Override
	public Type getType() {
		return CobolBaseType.BOOLEAN;
	}

	@Override
	public Object getValue() {
		return null;
	}

	@Override
	public void setClassCall(final Call classCall) {
		this.classCall = classCall;
	}

	@Override
	public void setClassConditionType(final ClassConditionType classConditionType) {
		this.classConditionType = classConditionType;
	}

	@Override
	public void setIdentifierCall(final Call identifierCall) {
		this.identifierCall = identifierCall;
	}

	@Override
	public void setNot(final boolean not) {
		this.not = not;
	}
}
