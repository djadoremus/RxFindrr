const functions = require('firebase-functions');
var admin = require("firebase-admin");
var serviceAccount = require("./rxfindrr-firebase-adminsdk-eef9b-aaa3312126.json");

admin.initializeApp({
  projectId: "rxfindrr",
  credential: admin.credential.cert(serviceAccount),
  databaseURL: "https://rxfindrr.firebaseio.com"
});

exports.sendUpdateProfileNotification = functions.auth.user().onCreate((user) => {
	console.log(JSON.stringify(user));
	//getUser in Cloud Firestore, get registrationToken
	var db = admin.firestore();

	return db.collection("users").doc(user.uid).get().then(doc => {
		if (!doc.exists){
			console.error("no document data ");
			return;
		} else {
			console.log ("document data ", doc.data());
	
			var message = {
				data: {
					foo: "bar",
					lorem: "ipsum"
				},
				token:doc.data().registrationToken 
			};
			return admin.messaging().send(message).then((response) => {
				console.log("successfully sent message ", response);
				return
			}).catch((error) => {
				console.log("error sending message ", error);
			});
		}
	}).catch(err => {
		console.log ("error getting document ", err);
	});
});

exports.notifyPharmacyOfReservation = functions.firestore.document("orders/{id}").onWrite((change, context) => {
	/*
		1. Get drugstore ID from order
		2. Get drugstore data from Firestore, get their registrationToken
		3. Call FCM
		4. ???
		5. PROFIT!!!
	*/
	var db = admin.firestore();
	return db.collection("pharmacy").doc("userid").get().then(doc => {
		if (!doc.exists){
			console.error("no document data ");
			return;
		} else {
			console.log ("document data ", doc.data());
	
			var message = {
				data: {
					foo: "bar",
					lorem: "ipsum"
				},
				token:doc.data().registrationToken 
			};
			return admin.messaging().send(message).then((response) => {
				console.log("successfully sent message ", response);
				return;
			}).catch((error) => {
				console.log("error sending message ", error);
			});
		}
	}).catch(error => {
		console.log ("error getting document ", err);
	});
});
