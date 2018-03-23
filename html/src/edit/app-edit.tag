require('./person-edit.tag');
require('./contacts-edit.tag');
require('./conditions-edit.tag');
require('./info-edit.tag');
<app-edit>
    <style>
        app-edit {
            width: 100%;
            height: 100%;
            flex-direction: column;
            display: flex;
            overflow: hidden;
        }
        #content {
            flex: 1;
            background-color: lightgrey;
            padding-left: 20px;
            overflow: auto;
            position: relative;
            top: 0px;
            left: 0px;
        }
        #content > * {
            display: none;
        }
        ul li {
            list-style-type: none;
        }
        ul {
            margin: 0px;
            padding: 0px;
            background-color: blue;
            display: flex;
            height: 80px;
        }
        li {
            height: 100%;
            color: white;
            display: block;
            margin: 0px;
            display: block;
            width: 80px;
        }
        span.spacer {
            flex: 1;
        }
        li > a {
            display: block;
            width: 100%;
            height: 100%;
            color: white;
            text-align: center;            
        }
        li > button {
            display: block;
            width: 80px;;
            height: 100%;
            text-align: center;
        }
        .selectedButton {
            background-color: lightblue;
            border-top: 1px solid red;
            padding-top: 2px;
        }
    </style>
    <div id="content">
        <person-edit id="self" person={patient.self} />
        <contacts-edit id="contacts" people={patient.contacts} />
        <conditions-edit id="conditions" conditions={patient.conditions} />
        <info-edit id="info" patient={patient} /> <!--Need object reference for dynamic edits-->
    </div>
    <ul>
        <span class="spacer"/>
        <li><a onclick={route} id="selfButton">Self</a></li>
        <li><a onclick={route} id="contactsButton">Contacts</a></li>
        <li><a onclick={route} id="conditionsButton">Conditions</a></li>
        <li><a onclick={route} id="infoButton">Info</a></li>
        <span class="spacer"/>
        <li><button onclick={submitPatient}>=&gt;</button></li>
    </ul>
    <script>
        var self = this;
        var patientcoder = require('../patientcoder');
        var selected;
        route (e) {
            if(selected) {
                document.getElementById(selected+'Button').classList.toggle('selectedButton');
                document.getElementById(selected).style.display = "none";
            }
            var id = e.target.textContent.toLowerCase().trim();
            selected = id;
            document.getElementById(selected+'Button').classList.toggle('selectedButton');
            document.getElementById(selected).style.display = "block";
        }
        self.on('mount', function () {
            document.getElementById('selfButton').click();
            self.update();
        });
         self.patient = {
            self: {
                name: "Bob bob",
                phone: "1234567890"
            },
            contacts: [
                {
                    name: "Jim jim",
                    phone: "1237894560"
                },
                {
                    name: "Joe joe",
                    phone: "1231231234"
                }
            ],
            conditions: [
                "peanut allergy",
                "addiction to dank memes"
            ],
            info: "my favorite pie tastes like pumpkins"
        };
        submitPatient () {
            var str = patientcoder.makeString(self.patient);
            Android.submitPatient(str);
        }
    </script>
</app-edit>