package by.training.command;

import by.training.command.impl.*;

/**
 * Created by angelina on 23.02.2017.
 */
public enum CommandType {
    SIGNIN {
        {
            this.command = new SignInCommand();
        }
    },
    SIGNUP {
        {
            this.command = new SignUpCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    SHOWPROFILE{
        {
            this.command = new ShowProfileCommand();
        }
    },
    EDITPROFILE{
        {
            this.command = new EditProfileCommand();
        }
    },
    LOAD {
        {
            this.command = new LoadCommand();
        }
    },
    REPLENISH{
        {
            this.command = new ReplenishAccountCommand();
        }
    },
    SHOWCREDITACCOUNT{
        {
                this.command = new ShowCreditAccountCommand();
        }
    },
    LOCALE{
        {
            this.command = new LocaleCommand();
        }
    },
    MONEYSETTING{
        {
            this.command = new MoneySettingCommand();
        }
    },
    NEWMESSAGE{
        {
            this.command = new NewMessageCommand();
        }
    },
    SHOWINBOX{
        {
            this.command = new ShowInboxCommand();
        }
    },
    SHOWSENTMAIL{
        {
            this.command = new ShowSentMailCommand();
        }
    };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
