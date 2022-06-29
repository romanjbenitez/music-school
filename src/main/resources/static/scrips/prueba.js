Vue.createApp({
    data() {
        return{
            arrayObjectMerch: [],
    arrayObjectCourse: [],
    arrayJSONmerch: "",
    arrayJSONcourse: "",
    email: "",
    password: "",
    idTicket: "" 

        }
    },
    created(){
        this.arrayObjectMerch = [
            {
                "id": 1,
                "quantity": 3
            },
            {
                "id": 2,
                "quantity": 4
            }
        ]



        this.arrayObjectCourse = [1] 
        this.arrayJSONmerch = JSON.stringify(this.arrayObjectMerch)
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
            var data = JSON.stringify(this.arrayObjectMerch);

            await axios.post(`/api/ticket_transaction?idsCourses=${this.arrayObjectCourse}`, this.arrayJSONmerch, {headers:{'content-type':'application/json'}})
            .then(function (response) {
                this.idTicket = response?.data
                axios.get(`/pdf/generate/${this.idTicket}`)
                .then(function(response){
                    console.log("1")
                    location.href=`/pdf/generate/${this.idTicket}`
                    
                })
                .catch(function (error) {
                    console.log(error);
        }); 
                // console.log(typeof response.data);
                // console.log("COMPLETE")
                })
            .catch(function (error) {
            console.log(error);
            });

        //     await axios.post("/api/ticket_transaction",{
        //         params: {
        //             idsCourses: this.arrayObjectCourse
        //         },
        //         data: this.arrayObjectMerch,
        //         dataType: "json",
        //         contentType:'application/json'
        //     })
        //     .then(response => {
        //      console.log("response")
        //     }
        //     )
        //     .catch(function (error) {
        //      if (error.response) {

        //        console.log(error.response.data);
        //        console.log(error.response.status);
        //        console.log(error.response.headers);
        //      } else if (error.request) {

        //        console.log("error.request");
        //      } else {
        //         console.log('Error F', error.message);
        //      }
        //      console.log(error.config);
        //    });
         },
         async descargarPDF(id){
        axios.get(`/pdf/generate/${id}`)
        .then(function(response){
            console.log(response);
        })
        .catch(function (error) {
            console.log(error);
        }); 
        }

    }
}).mount('#app')