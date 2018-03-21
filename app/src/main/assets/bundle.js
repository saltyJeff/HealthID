riot.tag2('app-display', '<ul> <li><a onclick="{route}" id="selfButton">Self</a></li> <li><a onclick="{route}" id="contactsButton">Contacts</a></li> </ul> <div id="content"> <h1 id="self" onclick="route">Self</h1> <h1 id="contacts" onclick="route">Contacts</h1> </div>', 'app-display { width: 100%; height: 100%; position: relative; flex-direction: row; display: flex; } app-display #content,[data-is="app-display"] #content{ flex: 1; background-color: green; padding-left: 20px; } app-display #content > *,[data-is="app-display"] #content > *{ display: none; } app-display ul li,[data-is="app-display"] ul li{ width: 60px; list-style-type: none; } app-display ul,[data-is="app-display"] ul{ margin: 0px; padding: 0px; background-color: blue; } app-display a,[data-is="app-display"] a{ border: 1px solid white; height: 60px; color: white; display: block; margin-bottom: 10px; } app-display .selectedButton,[data-is="app-display"] .selectedButton{ background-color: lightblue; }', '', function(opts) {
        var self = this;
        var selected;
        this.route = function (e) {
            if(selected) {
                document.getElementById(selected+'Button').classList.toggle('selectedButton');
                document.getElementById(selected).style.display = "none";
            }
            var id = e.target.textContent.toLowerCase().trim();
            selected = id;
            document.getElementById(selected+'Button').classList.toggle('selectedButton');
            document.getElementById(selected).style.display = "block";
        }.bind(this)
        self.on('mount', function () {
            document.getElementById('selfButton').click();
        });
});