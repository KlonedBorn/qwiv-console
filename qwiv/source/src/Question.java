

import java.util.Scanner;

public abstract class Question {
    protected String prompt, response , answers[];
    protected boolean isAnswered;
    
    public Question(){}

    public Question(String prompt, String answers[]) {
        this.prompt = prompt;
        this.answers = answers;
    }

    public boolean checkResponse(String r)
    {
        for( String a : this.answers ) {
            if( r.equals(a) ) this.isAnswered = true;
        }
        this.response = r;
        return this.isAnswered;
    }

    abstract public void ask(Scanner sc); 

    abstract public void readFromText(String s);

    public void reset()
    {
        this.response = null;
        this.isAnswered = false;
    }
}