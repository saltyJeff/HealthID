riot.tag2('app-display', '<div id="content"> <person-view id="self" person="{patient.self}"></person-view> <contacts-view id="contacts" people="{patient.contacts}"></contacts-view> </div> <ul> <span class="spacer"></span> <li><a onclick="{route}" id="selfButton">Self</a></li> <li><a onclick="{route}" id="contactsButton">Contacts</a></li> <span class="spacer"></span> </ul>', 'app-display { width: 100%; height: 100%; position: relative; flex-direction: column; display: flex; overflow: hidden; } app-display #content,[data-is="app-display"] #content{ flex: 1; background-color: lightgrey; padding-left: 20px; overflow: auto; } app-display #content > *,[data-is="app-display"] #content > *{ display: none; } app-display ul li,[data-is="app-display"] ul li{ list-style-type: none; } app-display ul,[data-is="app-display"] ul{ margin: 0px; padding: 0px; background-color: blue; display: flex; height: 80px; } app-display li,[data-is="app-display"] li{ height: 100%; color: white; display: block; margin: 0px; display: block; width: 80px; } app-display span.spacer,[data-is="app-display"] span.spacer{ flex: 1; } app-display a,[data-is="app-display"] a{ display: block; width: 100%; height: 100%; color: white; text-align: center; } app-display .selectedButton,[data-is="app-display"] .selectedButton{ background-color: lightblue; border-top: 1px solid red; padding-top: 2px; }', '', function(opts) {
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
            self.patient = parsePatient();
        });
        function parsePatient(s) {

        }
});
riot.tag2('contacts-view', '<person-view each="{opts.people}" person="{this}"></person-view>', '', '', function(opts) {
});
riot.tag2('person-view', '<h2>{opts.person.name}</h2> <h2><a href="{⁗tel:⁗+opts.person.phone}">{opts.person.phone}</a></h2>', 'person-view a,[data-is="person-view"] a{ text-align: left; color: purple; }', '', function(opts) {
});