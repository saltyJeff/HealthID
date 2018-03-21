<app-display>
    <style>
        app-display {
            width: 100%;
            height: 100%;
            position: relative;
            flex-direction: row;
            display: flex;
        }
        #content {
            flex: 1;
            background-color: green;
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
        }
        a {
            border: 1px solid white;
            height: 60px;
            color: white;
            display: block;
            margin-bottom: 10px;
        }
        .selectedButton {
            background-color: lightblue;
        }
    </style>
    <ul>
        <li><a onclick={route} id="selfButton">Self</a></li>
        <li><a onclick={route} id="contactsButton">Contacts</a></li>
    </ul>
    <div id="content">
        <h1 id="self" onclick="route">Self</h1>
        <h1 id="contacts" onclick="route">Contacts</h1>
    </div>
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