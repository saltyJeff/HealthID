<info-edit>
	<div>
		<textarea style="width: 80%; height: 500px;" oninput={handleInput} value={opts.patient.info}></textarea>
	</div>
	<script>
		var self = this;
		handleInput(e) {
			self.opts.patient.info = e.target.value;
		}
	</script>
</info-edit>