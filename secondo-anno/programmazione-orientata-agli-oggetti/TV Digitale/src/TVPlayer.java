public class TVPlayer extends Settings{
    private Channel mainChannel = null;
    private Channel secondaryChannel = null;
    private Settings settings = new Settings();

    /**
     * Play channel as main if nothing is playing else
     * check if two in one mode is on: if true play
     * channel as the secondary one else change main
     * channel.
     * @param channel channel to play
     */
    public void playChannel(Channel channel){
        Channel channelToPlay = ChannelsList.channels.get(ChannelsList.channels.indexOf(channel));
        if(mainChannel == null || !settings.twoInOneOn){
            mainChannel = channelToPlay;
        }else{
            if(settings.twoInOneOn) {
                secondaryChannel = channelToPlay;
            }
        }
    }

    /**
     * Enables two in one mode.
     */
    public void enableTwoInOne(){
        settings.twoInOneOn = true;
    }

    /**
     * Disables two in one mode;
     * if a secondary channel is playing set it to null.
     */
    public void disableTwoInOne(){
        settings.twoInOneOn = false;
        secondaryChannel = null;
    }

}
