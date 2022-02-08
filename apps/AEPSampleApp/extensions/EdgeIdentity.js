/*
Copyright 2021 Adobe. All rights reserved.
This file is licensed to you under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License. You may obtain a copy
of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software distributed under
the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR REPRESENTATIONS
OF ANY KIND, either express or implied. See the License for the specific language
governing permissions and limitations under the License.
@flow
@format
*/

import React, { Component } from 'react';
import { Button, StyleSheet, Text, View, ScrollView } from 'react-native';
import { AEPIdentity } from '@adobe/react-native-aepedgeidentity';

export default EdgeIdentity = ({ navigation }) => {

  return (
    <View style={styles.container}>
      <ScrollView contentContainerStyle={{ marginTop: 75 }}>
        <Button onPress={() => navigation.goBack()} title="Go to main page" />
        <Text style={styles.welcome}>EdgeIdentity</Text>
        <Button title="extensionVersion()" onPress={edgeIdentityExtensionVersion} />
        <Button title="getExperienceCloudId()" onPress={getExperienceCloudId} />
      </ScrollView>
    </View>
  )
}

function edgeIdentityExtensionVersion() {
  AEPIdentity.extensionVersion().then(version => console.log("AdobeExperienceSDK: AEPEdgeIdentity version: " + version));
}

function getExperienceCloudId() {
  AEPIdentity.getExperienceCloudId().then(experienceCloudId => console.log("AdobeExperienceSDK: Experience Cloud Id = " + experienceCloudId));
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 22,
    textAlign: 'center',
    margin: 10,
  }
});