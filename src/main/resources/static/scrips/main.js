Vue.createApp({
    data() {
      return {
        header : null ,
        studentName : "",
        studentCourse : "",
        studentImg : "",
        studentReview : "",
        }
      },

    created() {
    },
        
    mounted(){
      this.$nextTick(function () {
        this.header = document.querySelector(".nav");
      })
      this.filterStudent(0)


    },
    methods: {
      filterStudent(studentNumber){
        let  students = [{
            name :'Riley Douglas',
            course : 'Piano',
            img: './assets/riley.png',
            review : 'Thanks to the piano course of this academy, I can fulfill my dream of playing professionally in a rock band, excellent quality of teachers'
          },
          {
            name :'Harvey Walker',
            course : 'Guitar',
            img: './assets/harvey.png',
            review : 'Since I was a child I always wanted to play the guitar, thanks to the guitar course that has become a reality. I am very happy with the quality of information and practical exercises that I have been given at the academy.'
          },
          {
            name :'Paul Gray',
            course : 'Vocal',
            img: './assets/paul.png',
            review : 'Honestly I had an excellent learning in such a short time, the teachers are super friendly and great connoisseurs of music and rock. Thank you!'
          },
          {
            name :'Emilia Bailey',
            course : 'bass',
            img: './assets/emilia.png',
            review : 'I started taking classes 2 weeks ago. For the moment very happy. They are very didactic and with excellent predisposition.'}
          ]
          this.studentName= students[studentNumber].name;
          this.studentCourse= students[studentNumber].course;
          this.studentImg= students[studentNumber].img;
          this.studentReview= students[studentNumber].review;
      },

      subscribeEmail(){
        const Toast = Swal.mixin({
          toast: true,
          position: 'top-end',
          showConfirmButton: false,
          timer: 3000,
          timerProgressBar: true,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
          }
        })
        
        Toast.fire({
          icon: 'success',
          title: 'Successfully subscribed!'
        })
      }

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



      },
    }).mount("#app")