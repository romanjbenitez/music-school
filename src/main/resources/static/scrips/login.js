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
			.then(console.log('registered'))
			.then(Swal.fire({
				title: 'Now confirm your mail!',
				text: 'Check your inbox',
				imageUrl: '/assets/modal-register.png',
				imageWidth: 400,
				imageHeight: 200,
				imageAlt: 'Custom image',
				})
			)
		},

	},


	computed: {
		
	}
	},
).mount("#app")