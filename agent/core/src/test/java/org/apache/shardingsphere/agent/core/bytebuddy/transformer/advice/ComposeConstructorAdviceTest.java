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

package org.apache.shardingsphere.agent.core.bytebuddy.transformer.advice;

import org.apache.shardingsphere.agent.core.plugin.TargetAdviceObject;
import org.apache.shardingsphere.agent.core.plugin.advice.ConstructorAdvice;
import org.apache.shardingsphere.agent.core.mock.advice.MockConstructorAdvice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.LinkedList;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public final class ComposeConstructorAdviceTest {
    
    private final ConstructorAdvice constructorAdvice = new MockConstructorAdvice();
    
    private ComposeConstructorAdvice actual;
    
    @Before
    public void setUp() {
        actual = new ComposeConstructorAdvice(Collections.singleton(constructorAdvice));
    }
    
    @Test
    public void assertOnConstructor() {
        TargetAdviceObject targetAdviceObject = mock(TargetAdviceObject.class);
        Object[] args = new Object[2];
        args[0] = new LinkedList<String>();
        actual.onConstructor(targetAdviceObject, args);
    }
}
