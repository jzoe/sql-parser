/**
 * Copyright © 2012 Akiban Technologies, Inc.  All rights
 * reserved.
 *
 * This program and the accompanying materials are made available
 * under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This program may also be available under different license terms.
 * For more information, see www.akiban.com or contact
 * licensing@akiban.com.
 *
 * Contributors:
 * Akiban Technologies, Inc.
 */

/* The original from which this derives bore the following: */

/*

   Derby - Class org.apache.derby.impl.sql.compile.CurrentDatetimeOperatorNode

   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to you under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

package com.akiban.sql.parser;

import com.akiban.sql.StandardException;

import java.sql.Types;

/**
 * The CurrentDatetimeOperator operator is for the builtin CURRENT_DATE,
 * CURRENT_TIME, and CURRENT_TIMESTAMP operations.
 *
 */
public class CurrentDatetimeOperatorNode extends ValueNode 
{
    public static enum Field {
        DATE("CURRENT DATE", Types.DATE),
        TIME("CURRENT TIME", Types.TIME),
        TIMESTAMP("CURRENT TIMESTAMP", Types.TIMESTAMP);

        String methodName;
        int jdbcTypeId;

        Field(String methodName, int jdbcTypeId) {
            this.methodName = methodName;
            this.jdbcTypeId = jdbcTypeId;
        }
    }

    private Field field;

    public void init(Object field) {
        this.field = (Field)field;
    }

    public Field getField() {
        return field;
    }

    /**
     * Fill this node with a deep copy of the given node.
     */
    public void copyFrom(QueryTreeNode node) throws StandardException {
        super.copyFrom(node);

        CurrentDatetimeOperatorNode other = (CurrentDatetimeOperatorNode)node;
        this.field = other.field;
    }

    public String toString() {
        return "methodName: " + field.methodName + "\n" +
            super.toString();
    }

    /**
     * {@inheritDoc}
     */
    protected boolean isEquivalent(ValueNode o) {
        if (isSameNodeType(o)) {
            CurrentDatetimeOperatorNode other = (CurrentDatetimeOperatorNode)o;
            return other.field == field;
        }
        return false;
    }
}
