Vue.createApp({
	data() {
	return {
        isLogin: false,
        header : null ,
        firstName : "",
        lastName : "",
        charging: true,
        hidden: "",
	}
	},

	created() {
        axios
      .get("/api/client/current").then(api => {
        this.firstName = api.data.firstName
        this.lastName = api.data.lastName
        this.isLogin = true;
      }).catch(err => null)
      setTimeout(() => { this.charging = false }, 1500)
	},
	
	mounted(){
    this.$nextTick(function () {
        this.header = document.querySelector(".nav");
        this.hidden = document.querySelectorAll('.hidden');
      })
	},
	methods: {
    logout() {
      axios
        .post("/api/logout")
        .then((response) => window.location.replace("./index.html"));
    },
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
          scroll() {
            if (this.hidden != null) {
              window.addEventListener("scroll", () => {
                let hidden = this.hidden
                let scrolltop = document.documentElement.scrollTop;
                for (let i = 0; i < hidden.length; i++) {
                  let top = hidden[i].offsetTop;
                  console.log(scrolltop )
                  if (top - 600 < scrolltop && scrolltop > 350) {
                    hidden[i].style.opacity = 1;
                    hidden[i].classList.add("showtop")
                  }
                }
              })
            }
          }
	}
	},
).mount("#app")