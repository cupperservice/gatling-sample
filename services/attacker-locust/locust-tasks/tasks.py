#!/usr/bin/env python

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


import uuid

from datetime import datetime
from locust import HttpLocust, TaskSet, task


class MetricsTaskSet(TaskSet):
    _deviceid = None

    def on_start(self):
        self._deviceid = str(uuid.uuid4())

    @task(1)
    def scenario1(self):
        response = self.login("login_id00", "password00")
        token = response["token"]
        self.logout(token)

    def login(self, login_id, password):
        response = self.client.post("/users/login",
            json={
                "login_id": login_id,
                "password": password
            },
            name="login")

        return response.json()

    def logout(self, token):
        response = self.client.post("/users/logout",
            headers={
                "Authorization": "Bearer " + token
            },
            name="logout")

class MetricsLocust(HttpLocust):
    task_set = MetricsTaskSet
    min_wait=1
    max_wait=1
