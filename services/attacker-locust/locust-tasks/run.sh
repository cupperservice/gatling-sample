#!/bin/bash

# Copyright 2015 Google Inc. All rights reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.


LOCUST="/usr/local/bin/locust"
LOCUS_OPTS="-f /locust-tasks/$TEST_FILE --host=$TARGET_HOST"
LOCUST_MODE=${LOCUST_MODE:-standalone}

if [[ "$LOCUST_MODE" = "master" ]]; then
    LOCUS_OPTS="$LOCUS_OPTS --master"
elif [[ "$LOCUST_MODE" = "master-without-ui" ]]; then
    LOCUS_OPTS="$LOCUS_OPTS --master --no-web -c $NUM_CLIENTS -r $HATCH_RATE -t $RUN_TIME --csv perf-test"
elif [[ "$LOCUST_MODE" = "worker" ]]; then
    LOCUS_OPTS="$LOCUS_OPTS --slave --master-host=$MASTER_HOST"
fi

echo "$LOCUST $LOCUS_OPTS"

$LOCUST $LOCUS_OPTS