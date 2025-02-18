/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.proxy.frontend.mysql.command.admin;

import org.apache.shardingsphere.db.protocol.mysql.packet.generic.MySQLOKPacket;
import org.apache.shardingsphere.db.protocol.packet.DatabasePacket;
import org.apache.shardingsphere.proxy.backend.communication.jdbc.connection.JDBCBackendConnection;
import org.apache.shardingsphere.proxy.backend.communication.jdbc.transaction.JDBCBackendTransactionManager;
import org.apache.shardingsphere.proxy.backend.session.ConnectionSession;
import org.apache.shardingsphere.proxy.backend.session.ServerPreparedStatementRegistry;
import org.apache.shardingsphere.proxy.backend.session.transaction.TransactionStatus;
import org.apache.shardingsphere.proxy.frontend.mysql.command.query.binary.MySQLServerPreparedStatement;
import org.apache.shardingsphere.transaction.api.TransactionType;
import org.junit.Test;
import org.mockito.MockedConstruction;

import java.sql.SQLException;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public final class MySQLComResetConnectionExecutorTest {
    
    @Test
    public void assertExecute() throws SQLException {
        ConnectionSession connectionSession = mock(ConnectionSession.class);
        JDBCBackendConnection backendConnection = mock(JDBCBackendConnection.class);
        when(connectionSession.getBackendConnection()).thenReturn(backendConnection);
        when(connectionSession.getTransactionStatus()).thenReturn(new TransactionStatus(TransactionType.LOCAL));
        when(connectionSession.getServerPreparedStatementRegistry()).thenReturn(new ServerPreparedStatementRegistry());
        int statementId = 1;
        connectionSession.getServerPreparedStatementRegistry().addPreparedStatement(statementId, new MySQLServerPreparedStatement("", null));
        Collection<DatabasePacket<?>> actual;
        try (MockedConstruction<JDBCBackendTransactionManager> ignored = mockConstruction(JDBCBackendTransactionManager.class)) {
            actual = new MySQLComResetConnectionExecutor(connectionSession).execute();
        }
        assertThat(actual.size(), is(1));
        assertThat(actual.iterator().next(), instanceOf(MySQLOKPacket.class));
        verify(connectionSession).setAutoCommit(true);
        verify(connectionSession).setDefaultIsolationLevel(null);
        verify(connectionSession).setIsolationLevel(null);
        assertNull(connectionSession.getServerPreparedStatementRegistry().getPreparedStatement(statementId));
    }
}
