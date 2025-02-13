/*
Copyright 2021 Adobe. All rights reserved.
This file is licensed to you under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License. You may obtain a copy
of the License at http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under
the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR REPRESENTATIONS
OF ANY KIND, either express or implied. See the License for the specific language
governing permissions and limitations under the License.
*/
package com.adobe.marketing.mobile.reactnative;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableArray;

import java.util.Map;

class RCTAEPArrayUtil {

    static Object[] toObjectArray(ReadableArray readableArray) {
        if (readableArray == null) {
            return null;
        }

        Object[] array = new Object[readableArray.size()];

        for (int i = 0; i < readableArray.size(); i++) {
            ReadableType type = readableArray.getType(i);

            switch (type) {
                case Null:
                    array[i] = null;
                    break;
                case Boolean:
                    array[i] = readableArray.getBoolean(i);
                    break;
                case Number:
                    array[i] = readableArray.getDouble(i);
                    break;
                case String:
                    array[i] = readableArray.getString(i);
                    break;
                case Map:
                    array[i] = RCTAEPMapUtil.toMap(readableArray.getMap(i));
                    break;
                case Array:
                    array[i] = RCTAEPArrayUtil.toObjectArray(readableArray.getArray(i));
                    break;
            }
        }

        return array;
    }

    static WritableArray toWritableArray(Object[] array) {
        if (array == null) {
            return null;
        }
        WritableArray writableArr = Arguments.createArray();

        for (int i = 0; i < array.length; i++) {
            Object value = array[i];

            if (value == null) {
                writableArr.pushNull();
            } else if (value instanceof Boolean) {
                writableArr.pushBoolean((Boolean) value);
            } else if (value instanceof Double) {
                writableArr.pushDouble((Double) value);
            } else if (value instanceof Integer) {
                writableArr.pushInt((Integer) value);
            } else if (value instanceof String) {
                writableArr.pushString((String) value);
            } else if (value instanceof Map) {
                writableArr.pushMap(RCTAEPMapUtil.toWritableMap((Map<String, Object>) value));
            } else if (value.getClass().isArray()) {
                writableArr.pushArray(RCTAEPArrayUtil.toWritableArray((Object[]) value));
            }
        }

        return writableArr;
    }

}
