riot.tag2('app-display', '<div id="content"> <person-view id="self" person="{window.patient.self}">Self</person-view> <h1 id="contacts">Contacts</h1> </div> <ul> <li></li> <li><a onclick="{route}" id="selfButton">Self</a></li> <li><a onclick="{route}" id="contactsButton">Contacts</a></li> <li></li> </ul>', 'app-display { width: 100%; height: 100%; position: relative; flex-direction: column; display: flex; } app-display #content,[data-is="app-display"] #content{ flex: 1; background-color: lightgrey; padding-left: 20px; } app-display #content > *,[data-is="app-display"] #content > *{ display: none; } app-display ul li,[data-is="app-display"] ul li{ width: 60px; list-style-type: none; } app-display ul,[data-is="app-display"] ul{ margin: 0px; padding: 0px; background-color: blue; display: flex; } app-display li,[data-is="app-display"] li{ height: 60px; color: white; display: block; margin: 0px; display: block; flex: 1; } app-display a,[data-is="app-display"] a{ display: block; width: 100%; height: 100%; color: white; text-align: center; } app-display .selectedButton,[data-is="app-display"] .selectedButton{ background-color: lightblue; border-top: 1px solid red; }', '', function(opts) {
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
riot.tag2('person-view', '<h2>{opts.person.name}</h2> <h2>{opts.person.phone}</h2>', '', '', function(opts) {
});