Vue.createApp({
	data() {
		return {
			container : null,
			firstName: "",
			lastName: "",
			email: "",
			password: "",
		}
	},


	created() {
	},
	

	mounted(){

	this.$nextTick(function () {
	this.container = document.getElementById('login-container')
	})
	setTimeout(() => {
		this.container.classList.add('sign-in')
		}, 200)

	},


	methods: {

		toggle(){
			this.container.classList.toggle('sign-in')
			this.container.classList.toggle('sign-up')
		},

		signUp(){
			axios.post( "/api/login",`email=${this.email}&password=${this.password}`,
			{ headers: { "content-type": "application/x-www-form-urlencoded" } })
			.then((res) => {
					window.location.replace("./students.html")
			})
		},

		register(){
			axios.post('/api/clients',`firstName=${this.firstName}&lastName=${this.lastName}&email=${this.email}&password=${this.password}`,
			{headers:{'content-type':'application/x-www-form-urlencoded'}})
			.then(response => 
				console.log('registered'))
			//.then(this.signUp())
		},

	},


	computed: {
		
	}
	},
).mount("#app")