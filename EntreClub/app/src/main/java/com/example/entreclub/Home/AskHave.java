package com.example.entreclub.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.entreclub.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AskHave extends AppCompatActivity {

    Intent i;
    EditText e1,e2;
    Button btn;
    String type;
    RadioGroup radioGroup;
    RadioButton rb;
    FirebaseFirestore db;
    DocumentReference documentReference;
    FirebaseUser user;
    String current_user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_data);
        db= FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        current_user_id = user.getEmail();
        i=getIntent();
        final String eventId=i.getStringExtra("ID");
        documentReference=db.collection("Events").document(eventId).collection("asks").document(current_user_id);
        e1=(EditText) findViewById(R.id.email);
        btn=(Button)findViewById(R.id.btn_submit);
        e2=(EditText)findViewById(R.id.description);
        radioGroup=findViewById(R.id.rbtn);
        Toast.makeText(getApplicationContext(),eventId,Toast.LENGTH_LONG).show();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioid = radioGroup.getCheckedRadioButtonId();
                                       rb = findViewById(radioid);
                                       type = (String) rb.getText();
                                  //     Toast.makeText(getApplicationContext(), type, Toast.LENGTH_LONG).show();

                                       String desc = e2.getText().toString();


                String str2[] = desc.split(" ");
                Set<String> s1 =new HashSet<String>();
                for(int i=0;i<str2.length;i++)
                    s1.add(str2[i]);
             //   Log.d("subset:",str2[2]);
                Set<String> array = new HashSet<String>();
                array.addAll(Arrays.asList(new String[] {"abbo" , "abo" , "abortion" , "abuse" , "addict" , "addicts" , "adult" , "africa" , "african" , "alla" , "allah" , "alligatorbait" , "amateur" , "american" , "anal" , "analannie" , "analsex" , "angie" , "angry" , "anus" , "arab" , "arabs" , "areola" , "argie" , "aroused" , "arse" , "arsehole" , "asian" , "ass" , "assassin" , "assassinate" , "assassination" , "assault" , "assbagger" , "assblaster" , "assclown" , "asscowboy" , "asses" , "assfuck" , "assfucker" , "asshat" , "asshole" , "assholes" , "asshore" , "assjockey" , "asskiss" , "asskisser" , "assklown" , "asslick" , "asslicker" , "asslover" , "assman" , "assmonkey" , "assmunch" , "assmuncher" , "asspacker" , "asspirate" , "asspuppies" , "assranger" , "asswhore" , "asswipe" , "athletesfoot" , "attack" , "australian" , "babe" , "babies" , "backdoor" , "backdoorman" , "backseat" , "badfuck" , "balllicker" , "balls" , "ballsack" , "banging" , "baptist" , "barelylegal" , "barf" , "barface" , "barfface" , "bast" , "bastard" , "bazongas" , "bazooms" , "beaner" , "beast" , "beastality" , "beastial" , "beastiality" , "beatoff" , "beat-off" , "beatyourmeat" , "beaver" , "bestial" , "bestiality" , "bi" , "biatch" , "bible" , "bicurious" , "bigass" , "bigbastard" , "bigbutt" , "bigger" , "bisexual" , "bi-sexual" , "bitch" , "bitcher" , "bitches" , "bitchez" , "bitchin" , "bitching" , "bitchslap" , "bitchy" , "biteme" , "black" , "blackman" , "blackout" , "blacks" , "blind" , "blow" , "blowjob" , "boang" , "bogan" , "bohunk" , "bollick" , "bollock" , "bomb" , "bombers" , "bombing" , "bombs" , "bomd" , "bondage" , "boner" , "bong" , "boob" , "boobies" , "boobs" , "booby" , "boody" , "boom" , "boong" , "boonga" , "boonie" , "booty" , "bootycall" , "bountybar" , "bra" , "brea5t" , "breast" , "breastjob" , "breastlover" , "breastman" , "brothel" , "bugger" , "buggered" , "buggery" , "bullcrap" , "bulldike" , "bulldyke" , "bullshit" , "bumblefuck" , "bumfuck" , "bunga" , "bunghole" , "buried" , "burn" , "butchbabes" , "butchdike" , "butchdyke" , "butt" , "buttbang" , "butt-bang" , "buttface" , "buttfuck" , "butt-fuck" , "buttfucker" , "butt-fucker" , "buttfuckers" , "butt-fuckers" , "butthead" , "buttman" , "buttmunch" , "buttmuncher" , "buttpirate" , "buttplug" , "buttstain" , "byatch" , "cacker" , "cameljockey" , "cameltoe" , "canadian" , "cancer" , "carpetmuncher" , "carruth" , "catholic" , "catholics" , "cemetery" , "chav" , "cherrypopper" , "chickslick" , "children's" , "chin" , "chinaman" , "chinamen" , "chinese" , "chink" , "chinky" , "choad" , "chode" , "christ" , "christian" , "church" , "cigarette" , "cigs" , "clamdigger" , "clamdiver" , "clit" , "clitoris" , "clogwog" , "cocaine" , "cock" , "cockblock" , "cockblocker" , "cockcowboy" , "cockfight" , "cockhead" , "cockknob" , "cocklicker" , "cocklover" , "cocknob" , "cockqueen" , "cockrider" , "cocksman" , "cocksmith" , "cocksmoker" , "cocksucer" , "cocksuck" , "cocksucked" , "cocksucker" , "cocksucking" , "cocktail" , "cocktease" , "cocky" , "cohee" , "coitus" , "color" , "colored" , "coloured" , "commie" , "communist" , "condom" , "conservative" , "conspiracy" , "coolie" , "cooly" , "coon" , "coondog" , "copulate" , "cornhole" , "corruption" , "cra5h" , "crabs" , "crack" , "crackpipe" , "crackwhore" , "crack-whore" , "crap" , "crapola" , "crapper" , "crappy" , "crash" , "creamy" , "crime" , "crimes" , "criminal" , "criminals" , "crotch" , "crotchjockey" , "crotchmonkey" , "crotchrot" , "cum" , "cumbubble" , "cumfest" , "cumjockey" , "cumm" , "cummer" , "cumming" , "cumquat" , "cumqueen" , "cumshot" , "cunilingus" , "cunillingus" , "cunn" , "cunnilingus" , "cunntt" , "cunt" , "cunteyed" , "cuntfuck" , "cuntfucker" , "cuntlick" , "cuntlicker" , "cuntlicking" , "cuntsucker" , "cybersex" , "cyberslimer" , "dago" , "dahmer" , "dammit" , "damn" , "damnation" , "damnit" , "darkie" , "darky" , "datnigga" , "dead" , "deapthroat" , "death" , "deepthroat" , "defecate" , "dego" , "demon" , "deposit" , "desire" , "destroy" , "deth" , "devil" , "devilworshipper" , "dick" , "dickbrain" , "dickforbrains" , "dickhead" , "dickless" , "dicklick" , "dicklicker" , "dickman" , "dickwad" , "dickweed" , "diddle" , "die" , "died" , "dies" , "dike" , "dildo" , "dingleberry" , "dink" , "dipshit" , "dipstick" , "dirty" , "disease" , "diseases" , "disturbed" , "dive" , "dix" , "dixiedike" , "dixiedyke" , "doggiestyle" , "doggystyle" , "dong" , "doodoo" , "doo-doo" , "doom" , "dope" , "dragqueen" , "dragqween" , "dripdick" , "drug" , "drunk" , "drunken" , "dumb" , "dumbass" , "dumbbitch" , "dumbfuck" , "dyefly" , "dyke" , "easyslut" , "eatballs" , "eatme" , "eatpussy" , "ecstacy" , "ejaculate" , "ejaculated" , "ejaculating" , "ejaculation" , "enema" , "enemy" , "erect" , "erection" , "ero" , "escort" , "ethiopian" , "ethnic" , "european" , "evl" , "excrement" , "execute" , "executed" , "execution" , "executioner" , "explosion" , "facefucker" , "faeces" , "fag" , "fagging" , "faggot" , "fagot" , "failed" , "failure" , "fairies" , "fairy" , "faith" , "fannyfucker" , "fart" , "farted" , "farting" , "farty" , "fastfuck" , "fat" , "fatah" , "fatass" , "fatfuck" , "fatfucker" , "fatso" , "fckcum" , "fear" , "feces" , "felatio" , "felch" , "felcher" , "felching" , "fellatio" , "feltch" , "feltcher" , "feltching" , "fetish" , "fight" , "filipina" , "filipino" , "fingerfood" , "fingerfuck" , "fingerfucked" , "fingerfucker" , "fingerfuckers" , "fingerfucking" , "fire" , "firing" , "fister" , "fistfuck" , "fistfucked" , "fistfucker" , "fistfucking" , "fisting" , "flange" , "flasher" , "flatulence" , "floo" , "flydie" , "flydye" , "fok" , "fondle" , "footaction" , "footfuck" , "footfucker" , "footlicker" , "footstar" , "fore" , "foreskin" , "forni" , "fornicate" , "foursome" , "fourtwenty" , "fraud" , "freakfuck" , "freakyfucker" , "freefuck" , "fu" , "fubar" , "fuc" , "fucck" , "fuck" , "fucka" , "fuckable" , "fuckbag" , "fuckbuddy" , "fucked" , "fuckedup" , "fucker" , "fuckers" , "fuckface" , "fuckfest" , "fuckfreak" , "fuckfriend" , "fuckhead" , "fuckher" , "fuckin" , "fuckina" , "fucking" , "fuckingbitch" , "fuckinnuts" , "fuckinright" , "fuckit" , "fuckknob" , "fuckme" , "fuckmehard" , "fuckmonkey" , "fuckoff" , "fuckpig" , "fucks" , "fucktard" , "fuckwhore" , "fuckyou" , "fudgepacker" , "fugly" , "fuk" , "fuks" , "funeral" , "funfuck" , "fungus" , "fuuck" , "gangbang" , "gangbanged" , "gangbanger" , "gangsta" , "gatorbait" , "gay" , "gaymuthafuckinwhore" , "gaysex" , "geez" , "geezer" , "geni" , "genital" , "german" , "getiton" , "gin" , "ginzo" , "gipp" , "girls" , "givehead" , "glazeddonut" , "gob" , "god" , "godammit" , "goddamit" , "goddammit" , "goddamn" , "goddamned" , "goddamnes" , "goddamnit" , "goddamnmuthafucker" , "goldenshower" , "gonorrehea" , "gonzagas" , "gook" , "gotohell" , "goy" , "goyim" , "greaseball" , "gringo" , "groe" , "gross" , "grostulation" , "gubba" , "gummer" , "gun" , "gyp" , "gypo" , "gypp" , "gyppie" , "gyppo" , "gyppy" , "hamas" , "handjob" , "hapa" , "harder" , "hardon" , "harem" , "headfuck" , "headlights" , "hebe" , "heeb" , "hell" , "henhouse" , "heroin" , "herpes" , "heterosexual" , "hijack" , "hijacker" , "hijacking" , "hillbillies" , "hindoo" , "hiscock" , "hitler" , "hitlerism" , "hitlerist" , "hiv" , "ho" , "hobo" , "hodgie" , "hoes" , "hole" , "holestuffer" , "homicide" , "homo" , "homobangers" , "homosexual" , "honger" , "honk" , "honkers" , "honkey" , "honky" , "hook" , "hooker" , "hookers" , "hooters" , "hore" , "hork" , "horn" , "horney" , "horniest" , "horny" , "horseshit" , "hosejob" , "hoser" , "hostage" , "hotdamn" , "hotpussy" , "hottotrot" , "hummer" , "husky" , "hussy" , "hustler" , "hymen" , "hymie" , "iblowu" , "idiot" , "ikey" , "illegal" , "incest" , "insest" , "intercourse" , "interracial" , "intheass" , "inthebuff" , "israel" , "israeli" , "israel's" , "italiano" , "itch" , "jackass" , "jackoff" , "jackshit" , "jacktheripper" , "jade" , "jap" , "japanese" , "japcrap" , "jebus" , "jeez" , "jerkoff" , "jesus" , "jesuschrist" , "jew" , "jewish" , "jiga" , "jigaboo" , "jigg" , "jigga" , "jiggabo" , "jigger" , "jiggy" , "jihad" , "jijjiboo" , "jimfish" , "jism" , "jiz" , "jizim" , "jizjuice" , "jizm" , "jizz" , "jizzim" , "jizzum" , "joint" , "juggalo" , "jugs" , "junglebunny" , "kaffer" , "kaffir" , "kaffre" , "kafir" , "kanake" , "kid" , "kigger" , "kike" , "kill" , "killed" , "killer" , "killing" , "kills" , "kink" , "kinky" , "kissass" , "kkk" , "knife" , "knockers" , "kock" , "kondum" , "koon" , "kotex" , "krap" , "krappy" , "kraut" , "kum" , "kumbubble" , "kumbullbe" , "kummer" , "kumming" , "kumquat" , "kums" , "kunilingus" , "kunnilingus" , "kunt" , "ky" , "kyke" , "lactate" , "laid" , "lapdance" , "latin" , "lesbain" , "lesbayn" , "lesbian" , "lesbin" , "lesbo" , "lez" , "lezbe" , "lezbefriends" , "lezbo" , "lezz" , "lezzo" , "liberal" , "libido" , "licker" , "lickme" , "lies" , "limey" , "limpdick" , "limy" , "lingerie" , "liquor" , "livesex" , "loadedgun" , "lolita" , "looser" , "loser" , "lotion" , "lovebone" , "lovegoo" , "lovegun" , "lovejuice" , "lovemuscle" , "lovepistol" , "loverocket" , "lowlife" , "lsd" , "lubejob" , "lucifer" , "luckycammeltoe" , "lugan" , "lynch" , "macaca" , "mad" , "mafia" , "magicwand" , "mams" , "manhater" , "manpaste" , "marijuana" , "mastabate" , "mastabater" , "masterbate" , "masterblaster" , "mastrabator" , "masturbate" , "masturbating" , "mattressprincess" , "meatbeatter" , "meatrack" , "meth" , "mexican" , "mgger" , "mggor" , "mickeyfinn" , "mideast" , "milf" , "minority" , "mockey" , "mockie" , "mocky" , "mofo" , "moky" , "moles" , "molest" , "molestation" , "molester" , "molestor" , "moneyshot" , "mooncricket" , "mormon" , "moron" , "moslem" , "mosshead" , "mothafuck" , "mothafucka" , "mothafuckaz" , "mothafucked" , "mothafucker" , "mothafuckin" , "mothafucking" , "mothafuckings" , "motherfuck" , "motherfucked" , "motherfucker" , "motherfuckin" , "motherfucking" , "motherfuckings" , "motherlovebone" , "muff" , "muffdive" , "muffdiver" , "muffindiver" , "mufflikcer" , "mulatto" , "muncher" , "munt" , "murder" , "murderer" , "muslim" , "naked" , "narcotic" , "nasty" , "nastybitch" , "nastyho" , "nastyslut" , "nastywhore" , "nazi" , "necro" , "negro" , "negroes" , "negroid" , "negro's" , "nig" , "niger" , "nigerian" , "nigerians" , "nigg" , "nigga" , "niggah" , "niggaracci" , "niggard" , "niggarded" , "niggarding" , "niggardliness" , "niggardliness's" , "niggardly" , "niggards" , "niggard's" , "niggaz" , "nigger" , "niggerhead" , "niggerhole" , "niggers" , "nigger's" , "niggle" , "niggled" , "niggles" , "niggling" , "nigglings" , "niggor" , "niggur" , "niglet" , "nignog" , "nigr" , "nigra" , "nigre" , "nip" , "nipple" , "nipplering" , "nittit" , "nlgger" , "nlggor" , "nofuckingway" , "nook" , "nookey" , "nookie" , "noonan" , "nooner" , "nude" , "nudger" , "nuke" , "nutfucker" , "nymph" , "ontherag" , "oral" , "orga" , "orgasim" , "orgasm" , "orgies" , "orgy" , "osama" , "paki" , "palesimian" , "palestinian" , "pansies" , "pansy" , "panti" , "panties" , "payo" , "pearlnecklace" , "peck" , "pecker" , "peckerwood" , "pee" , "peehole" , "pee-pee" , "peepshow" , "peepshpw" , "pendy" , "penetration" , "peni5" , "penile" , "penis" , "penises" , "penthouse" , "period" , "perv" , "phonesex" , "phuk" , "phuked" , "phuking" , "phukked" , "phukking" , "phungky" , "phuq" , "pi55" , "picaninny" , "piccaninny" , "pickaninny" , "piker" , "pikey" , "piky" , "pimp" , "pimped" , "pimper" , "pimpjuic" , "pimpjuice" , "pimpsimp" , "pindick" , "piss" , "pissed" , "pisser" , "pisses" , "pisshead" , "pissin" , "pissing" , "pissoff" , "pistol" , "pixie" , "pixy" , "playboy" , "playgirl" , "pocha" , "pocho" , "pocketpool" , "pohm" , "polack" , "pom" , "pommie" , "pommy" , "poo" , "poon" , "poontang" , "poop" , "pooper" , "pooperscooper" , "pooping" , "poorwhitetrash" , "popimp" , "porchmonkey" , "porn" , "pornflick" , "pornking" , "porno" , "pornography" , "pornprincess" , "pot" , "poverty" , "premature" , "pric" , "prick" , "prickhead" , "primetime" , "propaganda" , "pros" , "prostitute" }));
                Set<String> intersection = new HashSet<String>(s1);
                intersection.retainAll(array);
                if(!intersection.isEmpty())
                {
                    //no abusive content
                    Toast.makeText(getApplicationContext(),"Description contains *Abusive Content*",Toast.LENGTH_SHORT);
                    finish();
                }


//                String mail=e1.getText().toString();
////                AddNeeds a=new AddNeeds(type,desc,mail);
//
//                                       documentReference.set(desc).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                           @Override
//                                           public void onSuccess(Void aVoid) {
//
//                                           }
//                                       });


                if(type.equals("Ask")) {
                    Map<String, Object> entrepreneur = new HashMap<>();
                    entrepreneur.put("Description", desc);
                    db.collection("Events").document(eventId).collection("asks").document(current_user_id).set(entrepreneur).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(),"Ask Saved!",Toast.LENGTH_SHORT);

                        }
                    });
                }

                else{
                    Map<String, Object> entrepreneur = new HashMap<>();
                    entrepreneur.put("Description", desc);
                    db.collection("Events").document(eventId).collection("haves").document(current_user_id).set(entrepreneur)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Toast.makeText(getApplicationContext(),"Have Saved!",Toast.LENGTH_SHORT);

                                }
                            });
                }





            }
        });
//        btn.setOnClickListener(new View.OnClickListener() {
//                                   @Override
//                                   public void onClick(View v) {
//                                       int radioid = radioGroup.getCheckedRadioButtonId();
//                                       rb = findViewById(radioid);
//                                       type = (String) rb.getText();
//                                       Toast.makeText(getApplicationContext(), type, Toast.LENGTH_LONG).show();
//
//                                       String desc = e2.getText().toString();
////                String mail=e1.getText().toString();
////                AddNeeds a=new AddNeeds(type,desc,mail);
//
//                                       documentReference.set(desc).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                           @Override
//                                           public void onSuccess(Void aVoid) {
//
//                                           }
//                                       });
//                                   }
//
//                               }
    }
}



//                CollectionReference dbProducts = db.collection("UpdateEventInfo");
//
//                dbProducts.add(a).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Toast.makeText(getApplicationContext(),"Data addded succesfully",Toast.LENGTH_LONG).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                    }
//                });

//            }
//        });

//
//            }
//
//        }
//    }
//}
