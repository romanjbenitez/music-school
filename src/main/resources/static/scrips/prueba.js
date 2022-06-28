Vue.createApp({
    data() {
        return{
            arrayObjectMerch: [],
    arrayObjectCourse: [],
    arrayJSONmerch: "",
    arrayJSONcourse: "",
    email: "",
    password: "",
    map: [],

        }
    },
    created(){
        this.map = new Map();
        this.map.set('1', 5)
        this.map.set('2', 10)





        this.arrayObjectCourse = [1,2,3,4,5] 
        this.arrayJSONmerch = new JSONObject(this.map)
        this.email = "dsada@gmail.com"
        this.password = "1234"

        this.loading = false;
    },
    methods:{
        async login(){
            await axios.post("/api/login", `email=${this.email}&password=${this.password}`)
            .then(response =>{
                console.log(response)
                console.log("autenticado")
            })
        },
        async ticketCompra(){
            await axios.post("/api/ticket_transaction", `idCourse=${this.arrayObjectCourse}&merches=${this.arrayJSONmerch}`, {headers:{'content-type':'application/x-www-form-urlencoded'}} )
            .then(response => {
             console.log("response")
            }
            )
            .catch(function (error) {
             if (error.response) {

               console.log(error.response.data);
               console.log(error.response.status);
               console.log(error.response.headers);
             } else if (error.request) {

               console.log("error.request");
             } else {
                console.log('Error F', error.message);
             }
             console.log(error.config);
           });
         }
    }
}).mount('#app')