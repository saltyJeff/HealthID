<conditions-edit>
	<style>
		div.condition {
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
	<div each={name, i in opts.conditions} class="condition">
		<input type="text" value={name} />
		<button class="del" onclick={delCondition(i)}>X</button>
    </div>
    <button onclick={addCondition} class="add">+</button>
    <script>
        var self = this;
        addCondition() {
            self.opts.conditions.push("Insert issue here");
        }
        delCondition(i) {
            return function () {
                self.opts.conditions.splice(i, 1);
            }
        }
	</script>
</conditions-edit>