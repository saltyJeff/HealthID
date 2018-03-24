riot.tag2('app-edit', '<div id="content"> <person-edit id="self" person="{patient.self}"></person-edit> <contacts-edit id="contacts" people="{patient.contacts}"></contacts-edit> <conditions-edit id="conditions" conditions="{patient.conditions}"></conditions-edit> <info-edit id="info" patient="{patient}"></info-edit> </div> <ul> <span class="spacer"></span> <li><a onclick="{route}" id="selfButton">Self</a></li> <li><a onclick="{route}" id="contactsButton">Contacts</a></li> <li><a onclick="{route}" id="conditionsButton">Conditions</a></li> <li><a onclick="{route}" id="infoButton">Info</a></li> <span class="spacer"></span> <li><button onclick="{submitPatient}">=&gt;</button></li> </ul>', 'app-edit { width: 100%; height: 100%; flex-direction: column; display: flex; overflow: hidden; } app-edit #content,[data-is="app-edit"] #content{ flex: 1; background-color: lightgrey; padding-left: 20px; overflow: auto; position: relative; top: 0px; left: 0px; } app-edit #content > *,[data-is="app-edit"] #content > *{ display: none; } app-edit ul li,[data-is="app-edit"] ul li{ list-style-type: none; } app-edit ul,[data-is="app-edit"] ul{ margin: 0px; padding: 0px; background-color: blue; display: flex; height: 80px; } app-edit li,[data-is="app-edit"] li{ height: 100%; color: white; display: block; margin: 0px; display: block; width: 80px; } app-edit span.spacer,[data-is="app-edit"] span.spacer{ flex: 1; } app-edit li > a,[data-is="app-edit"] li > a{ display: block; width: 100%; height: 100%; color: white; text-align: center; } app-edit li > button,[data-is="app-edit"] li > button{ display: block; width: 80px;; height: 100%; text-align: center; } app-edit .selectedButton,[data-is="app-edit"] .selectedButton{ background-color: lightblue; border-top: 1px solid red; padding-top: 2px; }', '', function(opts) {
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
            self.update();
        });
         self.patient = {
            self: {
                name: "John Doe",
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
                "blood type AB+",
                "history of heart attacks"
            ],
            info: "Keeps epipen in backpack"
        };
        this.submitPatient = function () {
            var patientStr = JSON.stringify(self.patient);
            var str = patientcoder.makeString(patientStr);
            Android.submitPatient(str);
        }.bind(this)
});
riot.tag2('conditions-edit', '<div each="{name, i in opts.conditions}" class="condition"> <input type="text" riot-value="{name}"> <button class="del" onclick="{delCondition(i)}">X</button> </div> <button onclick="{addCondition}" class="add">+</button>', 'conditions-edit div.condition,[data-is="conditions-edit"] div.condition{ display: flex; flex-direction: row; } conditions-edit button.add,[data-is="conditions-edit"] button.add{ position: absolute; bottom: 40px; right: 40px; width: 80px; height: 80px; }', '', function(opts) {
        var self = this;
        this.addCondition = function() {
            self.opts.conditions.push("Insert issue here");
        }.bind(this)
        this.delCondition = function(i) {
            return function () {
                self.opts.conditions.splice(i, 1);
            }
        }.bind(this)
});
riot.tag2('contacts-edit', '<div each="{person, index in opts.people}" class="person"> <person-edit person="{person}"></person-edit> <button class="del" onclick="{delPerson(index)}">X</button> </div> <button onclick="{addPerson}" class="add">+</button>', 'contacts-edit div.person,[data-is="contacts-edit"] div.person{ display: flex; flex-direction: row; } contacts-edit button.add,[data-is="contacts-edit"] button.add{ position: absolute; bottom: 40px; right: 40px; width: 80px; height: 80px; }', '', function(opts) {
        var self = this;
        this.addPerson = function() {
            self.opts.people.push({
                name: 'John Doe',
                phone: '0000000000'
            })
        }.bind(this)
        this.delPerson = function(i) {
            return function () {
                self.opts.people.splice(i, 1);
            }
        }.bind(this)
});
riot.tag2('info-edit', '<div> <textarea style="width: 80%; height: 500px;" oninput="{handleInput}" riot-value="{opts.patient.info}"></textarea> </div>', '', '', function(opts) {
		var self = this;
		this.handleInput = function(e) {
			self.opts.patient.info = e.target.value;
		}.bind(this)
});
riot.tag2('person-edit', '<input type="text" riot-value="{opts.person.name}" oninput="{handleName}"> <br> <input riot-value="{opts.person.phone}" oninput="{handleNumber}" type="{\'number\'}">', 'person-edit { margin: 3px; display: block; } person-edit a,[data-is="person-edit"] a{ text-align: left; color: purple; }', '', function(opts) {
		var self = this;
		this.handleName = function(e) {
			self.opts.person.name = e.target.value;
		}.bind(this)
		this.handleNumber = function(e) {
			if(e.target.value.toString().length > 11) {
				e.target.value = e.target.value.substr(0, 11);
			}
			if(isNaN(parseInt(e.target.value.length, 10))) {
				return;
			}
			self.opts.person.phone = e.target.value;
		}.bind(this)
});
riot.tag2('app-view', '<div id="content"> <person-view id="self" person="{patient.self}"></person-view> <contacts-view id="contacts" people="{patient.contacts}"></contacts-view> <conditions-view id="conditions" conditions="{patient.conditions}"></conditions-view> <info-view id="info" info="{patient.info}"></info-view> </div> <ul> <span class="spacer"></span> <li><a onclick="{route}" id="selfButton">Self</a></li> <li><a onclick="{route}" id="contactsButton">Contacts</a></li> <li><a onclick="{route}" id="conditionsButton">Conditions</a></li> <li><a onclick="{route}" id="infoButton">Info</a></li> <span class="spacer"></span> </ul>', 'app-view { width: 100%; height: 100%; position: relative; flex-direction: column; display: flex; overflow: hidden; } app-view #content,[data-is="app-view"] #content{ flex: 1; background-color: lightgrey; padding-left: 20px; overflow: auto; } app-view #content > *,[data-is="app-view"] #content > *{ display: none; } app-view ul li,[data-is="app-view"] ul li{ list-style-type: none; } app-view ul,[data-is="app-view"] ul{ margin: 0px; padding: 0px; background-color: blue; display: flex; height: 80px; } app-view li,[data-is="app-view"] li{ height: 100%; color: white; display: block; margin: 0px; display: block; width: 80px; } app-view span.spacer,[data-is="app-view"] span.spacer{ flex: 1; } app-view li > a,[data-is="app-view"] li > a{ display: block; width: 100%; height: 100%; color: white; text-align: center; } app-view .selectedButton,[data-is="app-view"] .selectedButton{ background-color: lightblue; border-top: 1px solid red; padding-top: 2px; }', '', function(opts) {
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
            self.patient = parsePatient(window.location.href) || {
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
            self.patient = JSON.parse(self.patient);
            console.log("TYPEOF: "+(typeof self.patient));
            self.update();
        });
        function parsePatient(href) {
            var questionIdx = href.indexOf('?');
            var strOfInterest = href.substring(questionIdx+1);
            if(questionIdx == -1 || !strOfInterest) {
                return undefined;
            }
            return patientcoder.makePatient(strOfInterest);
        }
});
riot.tag2('conditions-view', '<div each="{name, i in opts.conditions}"> {name} </div>', '', '', function(opts) {
		var self = this;
});
riot.tag2('contacts-view', '<person-view each="{opts.people}" person="{this}"></person-view>', '', '', function(opts) {
});
riot.tag2('info-view', '<div> <textarea readonly style="width: 80%; height: 500px;"> {opts.info} </textarea> </div>', '', '', function(opts) {
});
riot.tag2('person-view', '<h2>{opts.person.name}</h2> <h2><a href="{\'tel:\'+opts.person.phone}">{opts.person.phone}</a></h2>', 'person-view a,[data-is="person-view"] a{ text-align: left; color: purple; }', '', function(opts) {
});