require('./person-edit.tag');
<contacts-edit>
    <style>
        div.person {
            display: flex;
            flex-direction: row;
        }
        button.add {
            position: absolute;
            bottom: 40px;
            right: 40px;
            width: 80px;
            height: 80px;
        }
    </style>
    <div each={person, index in opts.people} class="person" >
        <person-edit person={person}/>
        <button class="del" onclick={delPerson(index)}>X</button>
    </div>
    <button onclick={addPerson} class="add">+</button>
    <script>
        var self = this;
        addPerson() {
            self.opts.people.push({
                name: 'John Doe',
                phone: '0000000000'
            })
        }
        delPerson(i) {
            return function () {
                self.opts.people.splice(i, 1);
            }
        }
    </script>
</contacts-edit>