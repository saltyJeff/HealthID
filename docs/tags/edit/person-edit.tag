<person-edit>
	<style>
		person-edit {
			margin: 3px;
			display: block;
		}
		a {
			text-align: left;
			color: purple;
		}
	</style>
	<input type="text" value={opts.person.name} oninput={handleName}/> <br>
	<input type="number" value={opts.person.phone} oninput={handleNumber}/>
	<script>
		var self = this;
		handleName(e) {
			self.opts.person.name = e.target.value;
		}
		handleNumber(e) {
			if(e.target.value.toString().length > 11) {
				e.target.value = e.target.value.substr(0, 11);
			}
			if(isNaN(parseInt(e.target.value.length, 10))) {
				return;
			}
			self.opts.person.phone = e.target.value;
		}
	</script>
</person-edit>