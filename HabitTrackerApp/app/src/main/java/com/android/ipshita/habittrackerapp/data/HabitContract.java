/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.ipshita.habittrackerapp.data;

import android.provider.BaseColumns;

/**
 * API Contract for the Pets app.
 */
public final class HabitContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private HabitContract() {}

    /**
     * Inner class that defines constant values for the pets database table.
     * Each entry in the table represents a single pet.
     */
    public static final class HabitEntry implements BaseColumns {

        public final static String TABLE_NAME = "habits";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_HABIT_NAME ="name";

        public final static String COLUMN_HABIT_PRIORITY= "priority";
    }

}

