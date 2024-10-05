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
package org.apache.dubbo.samples.extensibility.filter.provider;

import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.AsyncRpcResult;

public class AppendedFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Result result= invoker.invoke(invocation);
        // Obtain the returned value
        Result appResponse = ((AsyncRpcResult) result).getAppResponse();
        // Appended value
        if (appResponse.getValue() instanceof String) {
            appResponse.setValue(appResponse.getValue() + "'s customized AppendedFilter");
        }
        return result;
    }
}
