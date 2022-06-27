Vue.createApp({
	data() {
	return {
        isLogin: false,
        header : null ,
        firstName : "",
        lastName : "",
	}
	},

	created() {
        axios
      .get("/api/client/current").then(api => {
        this.firstName = api.data.firstName
        this.lastName = api.data.lastName
        this.isLogin = true;
      })

	},
	
	mounted(){
    this.$nextTick(function () {
        this.header = document.querySelector(".nav");
      })
	},
	methods: {
		
	},
	computed: {
		headershow(){
            if( this.header != null){
              window.addEventListener("scroll", () => {
                let header = this.header
                let scrolltop = document.documentElement.scrollTop;
                let top = header.offsetTop
                if(top + 100 <= scrolltop){
                    header.style.background = "#000000ff"
                }
                else{
                    header.style.background = "rgba(0, 0, 0, 0.0001)"
                }
              });      
            }
          },
	}
	},
).mount("#app")