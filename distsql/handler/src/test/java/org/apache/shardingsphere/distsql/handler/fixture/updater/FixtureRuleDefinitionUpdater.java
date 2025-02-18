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

package org.apache.shardingsphere.distsql.handler.fixture.updater;

import org.apache.shardingsphere.distsql.handler.fixture.config.FixtureRuleConfiguration;
import org.apache.shardingsphere.distsql.handler.fixture.statement.FixtureRuleStatement;
import org.apache.shardingsphere.distsql.handler.update.RuleDefinitionUpdater;
import org.apache.shardingsphere.infra.metadata.database.ShardingSphereDatabase;

public final class FixtureRuleDefinitionUpdater implements RuleDefinitionUpdater<FixtureRuleStatement, FixtureRuleConfiguration> {
    
    @Override
    public void checkSQLStatement(final ShardingSphereDatabase database, final FixtureRuleStatement sqlStatement, final FixtureRuleConfiguration currentRuleConfig) {
    }
    
    @Override
    public Class<FixtureRuleConfiguration> getRuleConfigurationClass() {
        return FixtureRuleConfiguration.class;
    }
    
    @Override
    public String getType() {
        return FixtureRuleStatement.class.getName();
    }
}
