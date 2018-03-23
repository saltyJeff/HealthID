require('./person-view.tag');
require('./contacts-view.tag');
<app-display>
    <style>
        app-display {
            width: 100%;
            height: 100%;
            position: relative;
            flex-direction: column;
            display: flex;
            overflow: hidden;
        }
        #content {
            flex: 1;
            background-color: lightgrey;
            padding-left: 20px;
            overflow: auto;
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
        .selectedButton {
            background-color: lightblue;
            border-top: 1px solid red;
            padding-top: 2px;
        }
    </style>
    <div id="content">
        <person-view id="self" person={patient.self} />
        <contacts-view id="contacts" people={patient.contacts} />
    </div>
    <ul>
        <span class="spacer"/>
        <li><a onclick={route} id="selfButton">Self</a></li>
        <li><a onclick={route} id="contactsButton">Contacts</a></li>
        <span class="spacer"/>
    </ul>
    <script>
        var self = this;
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
            self.patient = parsePatient() || {
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
                ]
            };
            self.update();
        });
        function parsePatient(s) {

        }
    </script>
</app-display>