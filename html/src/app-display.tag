<app-display>
    <style>
        app-display {
            width: 100%;
            height: 100%;
            position: relative;
            flex-direction: column;
            display: flex;
        }
        #content {
            flex: 1;
            background-color: lightgrey;
            padding-left: 20px;
        }
        #content > * {
            display: none;
        }
        ul li {
            width: 60px;
            list-style-type: none;
        }
        ul {
            margin: 0px;
            padding: 0px;
            background-color: blue;
            display: flex;
        }
        li {
            height: 60px;
            color: white;
            display: block;
            margin: 0px;
            display: block;
            flex: 1;
        }
        a {
            display: block;
            width: 100%;
            height: 100%;
            color: white;
            text-align: center;            
        }
        .selectedButton {
            background-color: lightblue;
            border-top: 1px solid red;
        }
    </style>
    <div id="content">
        <person-view id="self" person={window.patient.self}>Self</person-view>
        <h1 id="contacts">Contacts</h1>
    </div>
    <ul>
        <li/>
        <li><a onclick={route} id="selfButton">Self</a></li>
        <li><a onclick={route} id="contactsButton">Contacts</a></li>
        <li/>
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
        });
    </script>
</app-display>