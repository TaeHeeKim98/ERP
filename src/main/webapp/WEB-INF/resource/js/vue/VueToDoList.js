

    var todos = 
    [{"id":0,"title":"데이터1","completed":false}
    ,{"id":1,"title":"데이터2","completed":false}
    ,{"id":2,"title":"데이터3","completed":false}
    ,{"id":3,"title":"데이터4","completed":false}
    ,{"id":4,"title":"데이터5","completed":false}];
  
    var todoStorage = {
          fetch: function() {
            todos.forEach(function(todo, index) {
              todo.id = index;
            });  
            todoStorage.uid = todos.length;
            return todos;
          },
          save: function(todos) {
            var wantJson = JSON.stringify(todos);
            var todosWant = JSON.parse (wantJson || "[]");
          }
        };

var cmp3 = {
    template: `

    <div>
        <!-- 
        <input
        class="new-todo"
        autofocus
        autocomplete="off"
        placeholder="What needs to be done?"
        v-model="newTodo"
        @keyup.enter="addTodo"
        />
        -->
        
        
        <article class="all-browsers">
          <h1>todos</h1>
          <article class="browser">
            <h2>todo add</h2>
            <input id="input1" type="text" 
              autofocus
              autocomplete="off"
              placeholder="What needs to be done?"
              v-model="newTodo"
              @keyup.enter="addTodo"
            />
            <input id="input2" type="submit" @click="addTodo"/>
          </article>
          <article class="browser">
            <h2>todoList</h2>
            <div class="todo">
                <ul class="todo-list">
                    <li
                    v-for="todo in todos"
                    :class="[todo.completed == true? 'checked': '']"
                    :key="todo.id"
                    @click="doListClick(todo)"
                    >
                    {{todo.title}}
    
                    <span class="close" @click="doClose(todo)">×</span>
                    </li>
                </ul
            </div>
          </article>
        </article>




    </div>
    `,
    data () {
    return {
        todos: todoStorage.fetch(),
        newTodo: '',
        isChecked: '',
        }
    },
    methods: {
        addTodo() {
            var value = this.newTodo && this.newTodo.trim();
            if (!value) {
              return;
            }
            this.todos.push({
              id: todoStorage.uid++,
              title: value,
              completed: false
            });
            this.newTodo = "";
        },doListClick(clickDo){
            if(clickDo.completed){
                clickDo.completed = false;
            }else{
                clickDo.completed = true;
            }
            /*
            this.todos.forEach(function(todo) {
                if(clickDo){
                    todo.completed = true;
                }
            });
            */
        },doClose(clickDo){
            // json object 삭제
            this.todos.splice(this.todos.indexOf(clickDo), 1);
        }

    }, props:['propsdata'],
    created() {
    },watch: {
      todos: {
        handler: function(todos) {
          todoStorage.save(todos);
        },
        deep: true
      }
    },
};
